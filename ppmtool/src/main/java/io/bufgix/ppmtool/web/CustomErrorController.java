package io.bufgix.ppmtool.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.charset.Charset;

@Controller
public class CustomErrorController implements ErrorController {

    @Value("classpath:/static/index.html")
    Resource notFound;

    @Value("classpath:/error.html")
    Resource customError;

    @RequestMapping("/error")
    public ResponseEntity handleError(HttpServletRequest request) throws IOException {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        if(status != null) {
            Integer statusCode = Integer.valueOf(status.toString());

            if(statusCode == HttpStatus.NOT_FOUND.value()) {
                String body = StreamUtils.copyToString(notFound.getInputStream(), Charset.defaultCharset());
                return ResponseEntity.ok().contentType(MediaType.TEXT_HTML).body(body);
            }
        }
        String body = StreamUtils.copyToString(customError.getInputStream(), Charset.defaultCharset());
        return ResponseEntity.ok().contentType(MediaType.TEXT_HTML).body(body);
    }

}
