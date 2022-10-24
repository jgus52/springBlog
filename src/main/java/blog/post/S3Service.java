package blog.post;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class S3Service {

    private final AmazonS3 s3;

    @Autowired
    S3Service(AmazonS3 s3){
        this.s3 = s3;
    }

    public String upload(byte[] image){
        InputStream file = new ByteArrayInputStream(image);

        ObjectMetadata metadata = new ObjectMetadata();

        metadata.setContentLength(image.length);
        metadata.setContentType("image/jpeg");

        String curDate = LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME);

        String fileName = "jgBlog-"+curDate;

        s3.putObject("jgblog-image", fileName, file, metadata);

        return s3.getUrl("jgblog-image", fileName).toString();
    }
}
