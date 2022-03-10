package model.weather;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Headline {
    @JsonProperty("Text")
    private String text;

    @JsonProperty("EffectiveDate")
    public Date effectiveDate;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return effectiveDate + "\n" + text;
    }
}
