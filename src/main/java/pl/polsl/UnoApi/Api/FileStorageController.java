package pl.polsl.UnoApi.Api;

import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import pl.polsl.UnoApi.api.FileStorageApi;

import java.nio.file.Files;
@RestController
public class FileStorageController implements FileStorageApi {

    @Override
    public ResponseEntity<Resource> addImage(Resource body) {
        return ResponseEntity.ok().body(body);
    }
}
