package com.souclou.handling;

import com.souclou.error.ItemNotFoundException;
import java.net.URI;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class ApplicationExceptionHandler {

  @ExceptionHandler(ItemNotFoundException.class)
  public ProblemDetail handletNotFoundException(
    ItemNotFoundException ex,
    WebRequest request
  ) {
    ProblemDetail body = ProblemDetail.forStatusAndDetail(
      HttpStatusCode.valueOf(404),
      ex.getLocalizedMessage()
    );
    body.setType(URI.create("http://127.0.0.1:8182/errors/not-found"));
    body.setTitle("Element Introuvable");
    body.setProperty("hostname", "localhost");
    return body;
  }
}
