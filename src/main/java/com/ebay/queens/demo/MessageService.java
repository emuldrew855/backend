package com.ebay.queens.demo;

import java.util.Collection;
import com.ebay.queens.demo.Message;

public interface MessageService {

    public Message createMessage(Message msg);
    
    public boolean updateMessage(Message msg);
    
    public boolean deleteMessage(long id);

    public Message getMessage(long id);
    
    public Collection<Message> getMessages();
}
