import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class City {

    private String name;
    private String region;
    private String district;
    private int population;
    private String foundation;

    public City(String name, String region, String district, int population, String foundation) {
        this.name = name;
        this.region = region;
        this.district = district;
        this.population = population;
        this.foundation = foundation;
    }

    @Override
    public String toString() {
        return "City{name='" + this.name + '\''
                + ", region='" + this.region + '\''
                + ", district='" + this.district + '\''
                + ", population='" + this.population + '\''
                + ", foundation='" + this.foundation + '\''
                + "}";
    }
}
