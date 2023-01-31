package daedal.secondsto;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Arrays;
import java.util.List;

public final class SecondsTo extends PlaceholderExpansion {

    @Override
    public @NotNull String getIdentifier() {
        return "secondsto";
    }

    @Override
    public @NotNull String getAuthor() {
        return "Daedal";
    }

    @Override
    public @NotNull String getVersion() {
        return "1.0.0";
    }

    @Override
    public String onRequest(OfflinePlayer p, @NotNull String params) {
        if (params.length() != 5) {
            return "ФОРМАТ ВРЕМЕНИ ДОЛЖЕН БЫТЬ XX:XX";
        }
        if (!params.contains(":")) {
            return "ФОРМАТ ВРЕМЕНИ ДОЛЖЕН БЫТЬ XX:XX";
        }
        LocalDateTime currentTime = LocalDateTime.now();
        int currentHour = currentTime.getHour();
        int currentMinute = currentTime.getMinute();
        int currentSecond = currentTime.getSecond();
        List<String> toList = Arrays.asList(params.split(":"));
        int targetHour = Integer.parseInt(toList.get(0));
        int targetMinute = Integer.parseInt(toList.get(1));
        LocalDateTime targetTime;
        if (targetHour < currentHour && targetMinute < currentMinute) {
            targetTime = currentTime.plusDays(1).withHour(targetHour).withMinute(targetMinute);
        }
        else {
            targetTime = currentTime.withHour(targetHour).withMinute(targetMinute);
        }
        long unixCurrent = currentTime.toEpochSecond(ZoneOffset.UTC);
        long unixTarget = targetTime.toEpochSecond(ZoneOffset.UTC);
        long diff = unixTarget - unixCurrent;
        return diff-currentSecond + "s";
    }
}
