package codexstester.test.unitary;

import codexstester.setup.bridge.Help4DevsBridgeTests;
import com.huntercodexs.demo.dto.AwsS3RequestDto;
import com.huntercodexs.demo.services.Help4DevsAwsS3Service;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static codexstester.setup.datasource.MediaTests.ioFile;

public class Help4DevsAwsUnitaryTests extends Help4DevsBridgeTests {

    public static String path = "src/test/java/codexstester/setup/datasource";

    @Autowired
    Help4DevsAwsS3Service help4DevsAwsS3Service;

    @Test
    public void sendToS3Test() throws IOException {
        AwsS3RequestDto awsS3RequestDto = new AwsS3RequestDto();
        awsS3RequestDto.setFilename("");
        awsS3RequestDto.setData(ioFile(path+"/selfie.txt").getBytes(StandardCharsets.UTF_8));
        System.out.println(help4DevsAwsS3Service.saveToS3(awsS3RequestDto));
    }

    @Test
    public void readFromS3Test() {
        System.out.println(help4DevsAwsS3Service.readFromS3("41777918-5c4c-41e2-8fa3-20c2c8c2cecf"));
    }

}
