package io.swagger.api;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.*;
import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2022-02-24T01:48:22.638Z[GMT]")
@RestController
public class UploadfileApiController implements UploadfileApi {

    private static final Logger log = LoggerFactory.getLogger(UploadfileApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public UploadfileApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<Resource> uploadfilePost(@Parameter(in = ParameterIn.DEFAULT, description = "", required=true,schema=@Schema()) @RequestParam(value="operations", required=true)  Object operations,
                                                   @Parameter(description = "file detail") @Valid @RequestPart("file") MultipartFile fileName) {
        String accept = request.getHeader("Accept");
        log.info("Operations = " + operations.toString() + "On file = ", fileName.getOriginalFilename());
        String timeStamp = new SimpleDateFormat("yyyyMMddHHmm").format(new Date());

        String inFileName = "/Users/cd/Documents/project/" + timeStamp + "chandu.png";

        if (accept != null) {
            try {
                log.info("Chandu - About to process the file:");

                File file = new File(inFileName);
                fileName.transferTo(file);

                log.info("operations" + operations.toString());
                JSONObject jsonObject = new JSONObject(operations.toString());
                Iterator<?> iterator = jsonObject.keys();
                while (iterator.hasNext()) {
                    log.info("In while");
                    Object keyObj = iterator.next();
                    Object valueObj = jsonObject.get(keyObj.toString());
                    log.info("Key = " + keyObj.toString() + "; Value = " + valueObj.toString());
                    String key = keyObj.toString();
                    String value = valueObj.toString();

                    if(key.equals("rotate")){
                        ImageRotate rotateNew = new ImageRotate();
                        double angle = new Double(value);
                        log.info("Angle = " + String.valueOf(angle));
                        rotateNew.Execute(inFileName, inFileName, new Double(value));
                    } else if(key.equals("flip")) {
                        ImageFlip flipNew = new ImageFlip();
                        flipNew.Execute(inFileName, inFileName, value);
                    } else if(key.equals("generatethumbnail")){
                        ImageThumbnail ThumbnailNew  = new ImageThumbnail();
                        ThumbnailNew.Execute(inFileName, inFileName, new Integer(value));
                    } else if(key.equals("resize")){
                        value.trim();
                        String[] valArray = value.split("x", -1);
                        ImageResize resizeNew = new ImageResize();
                        resizeNew.Execute(inFileName, inFileName, Integer.parseInt(valArray[0]) ,Integer.parseInt(valArray[1]));
                        //resizeNew.Execute(inFileName, inFileName,200, 300);
                    } else if(key.equals("grayscale")){
                        ImageGrayScale grayScaleNew = new ImageGrayScale();
                        grayScaleNew.Execute(inFileName, inFileName);
                    } else {
                        //return new ResponseEntity<Resource>(HttpStatus.INTERNAL_SERVER_ERROR);
                    }
                }

                // return the processed image to the client
                final ByteArrayResource inputStream = new ByteArrayResource(Files.readAllBytes(Paths.get(inFileName)));
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.IMAGE_PNG);
                return new ResponseEntity<Resource>(inputStream, headers, HttpStatus.OK);

            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<Resource>(HttpStatus.INTERNAL_SERVER_ERROR);
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return new ResponseEntity<Resource>(HttpStatus.OK);
    }

}
