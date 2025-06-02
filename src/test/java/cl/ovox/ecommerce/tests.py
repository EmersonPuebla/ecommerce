from typing import Tuple, Optional
from enum import Enum
import requests

class HttpMethod(Enum):
    """
    Define los métodos HTTP soportados para las pruebas.
    Los valores se corresponden con los nombres de los métodos en minúsculas para su uso directo con 'requests'.
    """
    GET = "get"
    POST = "post"
    PUT = "put"
    DELETE = "delete"

class HttpContentType(Enum):
    """
    Define los tipos de contenido HTTP comunes para las cabeceras 'Content-Type'.
    Los valores son los MIME types estándar.
    """
    JSON = "application/json"
    XML = "application/xml"
    TEXT = "text/plain"
    FORM = "multipart/form-data"
    FORM_ENCODE = "application/x-www-form-urlencoded"
    GRAPHQL = "application/graphql"
    BINARY = "application/octet-stream"

class HttpStatus(int, Enum):
    """
    Enum de códigos de estado HTTP simplificado.
    Cada miembro representa un código de estado HTTP con su valor numérico.
    """
    # 1xx Informational
    CONTINUE = 100
    SWITCHING_PROTOCOLS = 101
    PROCESSING = 102
    EARLY_HINTS = 103

    # 2xx Success
    OK = 200
    CREATED = 201
    ACCEPTED = 202
    NON_AUTHORITATIVE_INFORMATION = 203
    NO_CONTENT = 204
    RESET_CONTENT = 205
    PARTIAL_CONTENT = 206
    MULTI_STATUS = 207
    ALREADY_REPORTED = 208
    IM_USED = 226

    # 3xx Redirection
    MULTIPLE_CHOICES = 300
    MOVED_PERMANENTLY = 301
    FOUND = 302
    SEE_OTHER = 303
    NOT_MODIFIED = 304
    USE_PROXY = 305 # Obsoleto, pero incluido para paridad si se requiere
    TEMPORARY_REDIRECT = 307
    PERMANENT_REDIRECT = 308

    # 4xx Client Error
    BAD_REQUEST = 400
    UNAUTHORIZED = 401
    PAYMENT_REQUIRED = 402
    FORBIDDEN = 403
    NOT_FOUND = 404
    METHOD_NOT_ALLOWED = 405
    NOT_ACCEPTABLE = 406
    PROXY_AUTHENTICATION_REQUIRED = 407
    REQUEST_TIMEOUT = 408
    CONFLICT = 409
    GONE = 410
    LENGTH_REQUIRED = 411
    PRECONDITION_FAILED = 412
    PAYLOAD_TOO_LARGE = 413
    URI_TOO_LONG = 414
    UNSUPPORTED_MEDIA_TYPE = 415
    REQUESTED_RANGE_NOT_SATISFIABLE = 416
    EXPECTATION_FAILED = 417
    I_AM_A_TEAPOT = 418
    UNPROCESSABLE_ENTITY = 422
    LOCKED = 423
    FAILED_DEPENDENCY = 424
    TOO_EARLY = 425
    UPGRADE_REQUIRED = 426
    PRECONDITION_REQUIRED = 428
    TOO_MANY_REQUESTS = 429
    REQUEST_HEADER_FIELDS_TOO_LARGE = 431
    UNAVAILABLE_FOR_LEGAL_REASONS = 451

    # 5xx Server Error
    INTERNAL_SERVER_ERROR = 500
    NOT_IMPLEMENTED = 501
    BAD_GATEWAY = 502
    SERVICE_UNAVAILABLE = 503
    GATEWAY_TIMEOUT = 504
    HTTP_VERSION_NOT_SUPPORTED = 505
    VARIANT_ALSO_NEGOTIATES = 506
    INSUFFICIENT_STORAGE = 507
    LOOP_DETECTED = 508
    BANDWIDTH_LIMIT_EXCEEDED = 509
    NOT_EXTENDED = 510
    NETWORK_AUTHENTICATION_REQUIRED = 511

    @classmethod
    def from_value(cls, value: int) -> 'HttpStatus':
        """
        Crea una instancia de HttpStatus a partir de su valor entero.
        Lanza un ValueError si el valor no corresponde a ningún HttpStatus.
        """
        for status in cls:
            if status.value == value:
                return status
        raise ValueError(f"{value} no es un código de estado HTTP válido.")

def RunTest(
    method: HttpMethod,
    endpoint: str,
    expected_status: int,
    content: Optional[str] = None, # Ahora es opcional y por defecto es None
    content_type: Optional[HttpContentType] = None # Ahora es opcional y por defecto es None
) -> Tuple[bool, str]:
    """
    Ejecuta una prueba HTTP contra un endpoint especificado.

    Args:
        method: El método HTTP a utilizar (GET, POST, PUT, DELETE).
        endpoint: La URL del endpoint a probar.
        expected_status: El código de estado HTTP esperado para una prueba exitosa.
        content: Opcional. El cuerpo de la solicitud (para métodos que lo requieren como POST, PUT).
                 Por defecto es None.
        content_type: Opcional. El tipo de contenido del cuerpo de la solicitud.
                      Por defecto es None.

    Returns:
        Una tupla donde el primer elemento es un booleano (True para éxito, False para fallo)
        y el segundo elemento es una cadena de texto con el detalle de la prueba.
    """
    headers = {} # Inicializamos las cabeceras vacías

    # Si se proporciona un content_type, lo añadimos a las cabeceras.
    # Esto es crucial para POST/PUT cuando se envía un cuerpo.
    if content_type:
        headers["Content-Type"] = content_type.value

    response_content = "" # Variable para almacenar el contenido de la respuesta

    try:
        # Realiza la solicitud HTTP según el método especificado.
        # Para POST y PUT, el 'data' (cuerpo de la solicitud) se envía solo si 'content' no es None.
        if method == HttpMethod.GET:
            response = requests.get(endpoint, headers=headers)
        elif method == HttpMethod.POST:
            # Si content no es None, lo enviamos como data
            response = requests.post(endpoint, headers=headers, data=content if content is not None else None)
        elif method == HttpMethod.PUT:
            # Si content no es None, lo enviamos como data
            response = requests.put(endpoint, headers=headers, data=content if content is not None else None)
        elif method == HttpMethod.DELETE:
            response = requests.delete(endpoint, headers=headers) # DELETE raramente lleva cuerpo
        else:
            return False, f"❌ Error: Método HTTP '{method.name}' no soportado."

        # Captura el contenido de la respuesta para incluirlo en el detalle.
        response_content = response.text

        # Compara el código de estado de la respuesta con el esperado.
        if response.status_code == expected_status:
            return True, (
                f"✅ Prueba Exitosa: HTTP {method.name} a {endpoint} retornó "
                f"el estado esperado {expected_status}. Respuesta: {response_content[:200]}..."
            )
        else:
            return False, (
                f"❌ Prueba Fallida: HTTP {method.name} a {endpoint}. "
                f"Se esperaba el estado {expected_status}, pero se obtuvo {response.status_code}. "
                f"Respuesta: {response_content}"
            )
    except requests.exceptions.ConnectionError as e:
        # Maneja errores de conexión (por ejemplo, si el host no es alcanzable).
        return False, f"❌ Prueba Fallida: Error de conexión a {endpoint}. Detalles: {e}"
    except requests.exceptions.Timeout as e:
        # Maneja errores de tiempo de espera agotado.
        return False, f"❌ Prueba Fallida: La solicitud excedió el tiempo de espera para {endpoint}. Detalles: {e}"
    except requests.exceptions.RequestException as e:
        # Captura cualquier otra excepción relacionada con 'requests'.
        return False, f"❌ Prueba Fallida: Ocurrió un error inesperado en la solicitud para {endpoint}. Detalles: {e}"
    except Exception as e:
        # Captura cualquier otra excepción general inesperada.
        return False, f"❌ Prueba Fallida: Ocurrió un error inesperado. Detalles: {e}"
    
get_all_categorias = RunTest(HttpMethod.GET, "http://localhost:5600/api/v1/crud/categorias", HttpStatus.OK)

print(get_all_categorias)