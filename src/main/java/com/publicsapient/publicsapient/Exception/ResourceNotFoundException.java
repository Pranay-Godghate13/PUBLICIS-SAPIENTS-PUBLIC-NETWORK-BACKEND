package com.publicsapient.publicsapient.Exception;

public class ResourceNotFoundException extends RuntimeException{

    private String resourceName;
    private String field;
    private String fieldName;
    private Long fieldId;
    
    public ResourceNotFoundException()
    {

    }

    public ResourceNotFoundException(String resourceName,String field,String fieldName)
    {
        super(String.format("%s not found %s:%s", resourceName,field,fieldName));
        this.resourceName=resourceName;
        this.field=field;
        this.fieldName=fieldName;
    }

    public ResourceNotFoundException(String field,String fieldName,Long fieldId)
    {
        super(String.format("%s not found %s:%d", fieldName,field,fieldId));
        this.field=field;
        this.fieldName=fieldName;
        this.fieldId=fieldId;
    }
}
