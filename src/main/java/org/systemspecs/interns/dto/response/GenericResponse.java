package org.systemspecs.interns.dto.response;

public class GenericResponse {
    private String code;
    private String message;
    private Object data;
    private String metadata;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMetadata() {
        return metadata;
    }

    public void setMetadata(String metadata) {
        this.metadata = metadata;
    }

    @Override
    public String toString() {
        return "GenericResponse{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", data=" + data +
                ", metadata='" + metadata + '\'' +
                '}';
    }
}
