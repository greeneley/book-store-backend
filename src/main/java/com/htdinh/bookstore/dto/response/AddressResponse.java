package com.htdinh.bookstore.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressResponse implements Serializable {
    @JsonProperty("address_id")
    Integer addressId;
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