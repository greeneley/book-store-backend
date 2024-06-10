package com.htdinh.bookstore.dto.request;

import com.htdinh.bookstore.model.User;
import lombok.Value;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Value
public class AddressRequest implements Serializable {
    @NotNull
    @Size(max = 250)
    String province;
    @NotNull
    @Size(max = 250)
    String district;
    @NotNull
    @Size(max = 250)
    String ward;
    @Size(max = 250)
    String orderReceiverAddress;
    @Size(max = 250)
    String receiverName;
    @Size(max = 250)
    String receiverPhone;
    @Size(max = 50)
    String createdAt;
}