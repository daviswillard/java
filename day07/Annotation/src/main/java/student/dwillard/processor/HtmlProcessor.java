package student.dwillard.processor;

import com.google.auto.service.AutoService;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.Set;
import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import student.dwillard.annotations.HtmlForm;
import student.dwillard.annotations.HtmlInput;

@SupportedAnnotationTypes(
    {
        "student.dwillard.annotations.HtmlForm",
        "student.dwillard.annotations.HtmlInput"
    }
)
@SupportedSourceVersion(SourceVersion.RELEASE_8)
@AutoService(Processor.class)
public class HtmlProcessor extends AbstractProcessor {

  private void createHtmlFile(Element element) {
    HtmlForm htmlForm = element.getAnnotation(HtmlForm.class);

    try {
      String fileName = htmlForm.fileName();
      fileName = fileName.replace("generated_sources", "");
      File file = new File("target/classes/" + fileName);
      file.createNewFile();
      try (
          FileWriter fileWriter = new FileWriter(file);
          PrintWriter writer = new PrintWriter(fileWriter)) {
        writer.printf("<form action = \"%s\" method = \"%s\">\n",
            htmlForm.action(), htmlForm.method());
        for (Element e : element.getEnclosedElements()) {
          HtmlInput htmlInput = e.getAnnotation(HtmlInput.class);
          if (htmlInput != null) {
            writer.printf("\t<input type = \"%s\" name = \"%s\" placeholder = \"%s\">\n",
                htmlInput.type(), htmlInput.name(), htmlInput.placeholder());
          }
        }
        writer.printf("<input type = %s value = %s>", "\"submit\"", "\"Send\"");
        writer.printf("</form>");
      }
    } catch (IOException ex) {
      throw new RuntimeException(ex);
    }
  }

  @Override
  public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv){

    Set<? extends Element> elements = roundEnv.getElementsAnnotatedWith(HtmlForm.class);
    for (Element element: elements) {
      createHtmlFile(element);
    }
    return false;
  }
}
