/**
 * @author yty
 * @version 1.0
 * @since 1.0
 **/
public class WeaponFactory {
    private WeaponFactory(){}
    public static Weapon getDagger(){
        return new Dagger();
    }

    public static Weapon getFighter(){
        return new Fighter();
    }
}
