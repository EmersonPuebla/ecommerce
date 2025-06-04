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
    content: Optional[str] = None,
    content_type: Optional[HttpContentType] = None
) -> str:
    headers = {}

    if content_type:
        headers["Content-Type"] = content_type.value

    try:
        if method == HttpMethod.GET:
            response = requests.get(endpoint, headers=headers)
        elif method == HttpMethod.POST:
            response = requests.post(endpoint, headers=headers, data=content if content is not None else None)
        elif method == HttpMethod.PUT:
            response = requests.put(endpoint, headers=headers, data=content if content is not None else None)
        elif method == HttpMethod.DELETE:
            response = requests.delete(endpoint, headers=headers)
        else:
            return f"\033[91m❌ Error: Método HTTP '{method.name}' no soportado.\033[0m \n\n"

        response_content = response.text

        if response.status_code == expected_status:
            # Mensaje de éxito con colores
            return (
                f"\033[92m✅ {method.name} {endpoint}\033[0m\n"
                f"      URL = \033[94m'{endpoint}'\033[0m\n"
                f"      MESSAGE = \033[93m'Se obtuvo el estado esperado {expected_status}'\033[0m\n"
                f"      EXPECTED = \033[92m{expected_status} OK\033[0m | RECEIVED = \033[92m{response.status_code} OK\033[0m\n"
                f"      CONTENT = \033[96m{response_content[:200]}...\033[0m \n\n"
            )
        else:
            # Mensaje de error con colores
            return (
                f"\033[91m❌ {method.name} {endpoint}\033[0m\n"
                f"      URL = \033[94m'{endpoint}'\033[0m\n"
                f"      MESSAGE = \033[93m'Se esperaba el estado {expected_status}, pero se obtuvo {response.status_code}'\033[0m\n"
                f"      EXPECTED = \033[91m{expected_status}\033[0m | RECEIVED = \033[91m{response.status_code}\033[0m\n"
                f"      CONTENT = \033[96m{response_content[:200]}...\033[0m \n\n"
            )
    except requests.exceptions.ConnectionError as e:
        return f"\033[91m❌ Prueba Fallida: Error de conexión a {endpoint}. Detalles: {e}\033[0m \n\n"
    except requests.exceptions.Timeout as e:
        return f"\033[91m❌ Prueba Fallida: La solicitud excedió el tiempo de espera para {endpoint}. Detalles: {e}\033[0m \n\n"
    except requests.exceptions.RequestException as e:
        return f"\033[91m❌ Prueba Fallida: Ocurrió un error inesperado en la solicitud para {endpoint}. Detalles: {e}\033[0m \n\n"
    except Exception as e:
        return f"\033[91m❌ Prueba Fallida: Ocurrió un error inesperado. Detalles: {e}\033[0m \n\n"


update_color_by_id = RunTest(HttpMethod.PUT,
                          "http://localhost:5600/api/v1/crud/colores/27",
                          HttpStatus.OK, '{"nombre" : "Blanco"}',
                          HttpContentType.JSON)

add_new_color = RunTest(HttpMethod.POST,
                       "http://localhost:5600/api/v1/crud/colores",
                       HttpStatus.OK,
                       '{"nombre" : "amarillo"}',
                       HttpContentType.JSON)

delete_color_by_id = RunTest(HttpMethod.DELETE,
                             "http://localhost:5600/api/v1/crud/colores/41",
                             HttpStatus.OK)

get_color_by_id = RunTest(HttpMethod.GET,
                          "http://localhost:5600/api/v1/crud/colores/27",
                          HttpStatus.OK)

get_all_colores = RunTest(HttpMethod.GET,
                          "http://localhost:5600/api/v1/crud/colores",
                          HttpStatus.OK)


print(update_color_by_id)
print(add_new_color)
print(delete_color_by_id)
print(get_all_colores)