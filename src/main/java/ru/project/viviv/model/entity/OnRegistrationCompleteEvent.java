package ru.project.viviv.model.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.context.ApplicationEvent;

@Data
@EqualsAndHashCode(callSuper = true)
public class OnRegistrationCompleteEvent extends ApplicationEvent {
    private User user;

    public OnRegistrationCompleteEvent(User user) {
        super(user);

        this.user = user;
    }
}