package ru.project.viviv.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import ru.project.viviv.model.entity.Profile;
import ru.project.viviv.model.service.ProfileService;
import ru.project.viviv.system.ControllerLogging;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private ProfileService profileService;

    private static final Logger LOGGER = LoggerFactory.getLogger(ControllerLogging.class);

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        String email = authentication.getName();
        LOGGER.info(email + " в onAuthenticationSuccess");
        Profile profile = profileService.findByEmail(email);
        HttpSession session = request.getSession();
        session.setAttribute("user", profile);
        response.sendRedirect(request.getContextPath() + "/");
    }
}