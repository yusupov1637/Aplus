package com.company.model;

import com.company.controller.MyEvosClone;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.telegram.telegrambots.meta.api.objects.Location;

import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TelergamBotUser {
    private String name;
    private String phoneNumber;
    private Location location;
    private Map<String,Double> meals;
    private MyEvosClone myEvosClone;
    private String bookedAt;
    private Double totalsum;

    public TelergamBotUser(MyEvosClone myEvosClone) {
        this.myEvosClone=myEvosClone;
    }

    @Override
    public String toString() {
        return "name='" + name + '\'' +
                ",\n phoneNumber='" + phoneNumber + '\'' +
                ",\n location=" + location +
                ",\n meals=" + meals +
                ",\n myEvosClone=" + myEvosClone +
                ",\n BookedAt=" + bookedAt +
                ",\n Total Sum=" + totalsum +"";
    }
}
