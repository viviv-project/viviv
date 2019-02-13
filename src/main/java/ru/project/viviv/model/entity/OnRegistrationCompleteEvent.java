package ru.project.viviv.model.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.context.ApplicationEvent;
import ru.project.viviv.model.entity.User;

@Data
@EqualsAndHashCode(callSuper = true)
public class OnRegistrationCompleteEvent extends ApplicationEvent {
    private String appUrl;
    private User user;

    public OnRegistrationCompleteEvent(User user, String appUrl) {
        super(user);

        this.user = user;
        this.appUrl = appUrl;
    }
}