package by.epam.trainig.tag;

import by.epam.trainig.entity.user.User;
import by.epam.trainig.entity.user.UserDetail;
import by.epam.trainig.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.Optional;

import static java.lang.String.format;

public class WelcomeUserTag extends TagSupport {

    private static final Logger LOGGER = LogManager.getLogger(WelcomeUserTag.class);

    private static final String PARAGRAPH_TAGS = "<p>%s, %s</p>";
    private static final String USER_SESSION_PARAM_NAME = "user";
    private static final String USER_ID = "id";

    private String text;

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public int doStartTag() {
        extractUserNameFromSession()
                .ifPresent(name -> printTextToOut(format(PARAGRAPH_TAGS, text, name)));
        return SKIP_BODY;
    }

    @Override
    public int doEndTag() {
        return EVAL_PAGE;
    }

    private Optional<String> extractUserNameFromSession() {

        final int userId = Optional.ofNullable(pageContext.getSession())
                .map(session -> (User) session.getAttribute(USER_SESSION_PARAM_NAME))
                .map(User::getId).get();

        return UserService.getInstance().findBy(USER_ID, userId)
                .map(user -> user.getUserDetail().getFirstName());


    }

    private void printTextToOut(String text) {

        final JspWriter out = pageContext.getOut();
        try {

            out.write(text);

        } catch (IOException e) {

            LOGGER.error("error occurred writing text to jsp. text: {}, tagId: {}", text, id);
            LOGGER.error(e);
        }
    }
}
