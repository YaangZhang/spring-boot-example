package com.chagee.domain.messaging;


import com.chagee.domain.modal.AuditMessage;

public interface AuditMessageProducer {
    void send(AuditMessage message);
}
