package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.core.io.Resource;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * UploadfileBody
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2022-02-24T01:48:22.638Z[GMT]")


public class UploadfileBody   {
  @JsonProperty("operations")
  private Object operations = null;

  @JsonProperty("fileName")
  private Resource fileName = null;

  public UploadfileBody operations(Object operations) {
    this.operations = operations;
    return this;
  }

  /**
   * Get operations
   * @return operations
   **/
  @Schema(description = "")
  
    public Object getOperations() {
    return operations;
  }

  public void setOperations(Object operations) {
    this.operations = operations;
  }

  public UploadfileBody fileName(Resource fileName) {
    this.fileName = fileName;
    return this;
  }

  /**
   * Get fileName
   * @return fileName
   **/
  @Schema(description = "")
  
    @Valid
    public Resource getFileName() {
    return fileName;
  }

  public void setFileName(Resource fileName) {
    this.fileName = fileName;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UploadfileBody uploadfileBody = (UploadfileBody) o;
    return Objects.equals(this.operations, uploadfileBody.operations) &&
        Objects.equals(this.fileName, uploadfileBody.fileName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(operations, fileName);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class UploadfileBody {\n");
    
    sb.append("    operations: ").append(toIndentedString(operations)).append("\n");
    sb.append("    fileName: ").append(toIndentedString(fileName)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}
