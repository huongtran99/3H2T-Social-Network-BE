package com.codegym.model.querry;

import java.util.Date;

public interface IUserChat {
    Long getId();
    String getAvatar();
    String getUsername();
    String getContent();
    Date getDatetime();
}
