package com.project.market.dto.req.payment;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Pattern;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"username", "startDate", "endDate"})
public class HistoryOrderDtoRequest {
    @JsonProperty("username")
    @ApiModelProperty(position = 1, required = false, dataType = "String", example = "username", notes = "Username")
    private String username;

    @Pattern(regexp = "((19|20|21)[0-9]{2}-((0(0|1|2|3|4|5|6|7|8|9))|(10|11|12))-(([0-2][0-9])|30|31))|(^$)", message = "Expected format YYYY-MM-DD")
    @JsonProperty("startDate")
    @ApiModelProperty(position = 2, required = false, dataType = "String", example = "2000-01-01", notes = "Start Date")
    private String startDate;

    @Pattern(regexp = "((19|20|21)[0-9]{2}-((0(0|1|2|3|4|5|6|7|8|9))|(10|11|12))-(([0-2][0-9])|30|31))|(^$)", message = "Expected format YYYY-MM-DD")
    @JsonProperty("endDate")
    @ApiModelProperty(position = 3, required = false, dataType = "String", example = "2000-01-01", notes = "End Date")
    private String endDate;
}
