/**
 * @author yty
 * @version 1.0
 * @since 1.0
 **/
public class FighterFactory extends WeaponFactory{
    @Override
    public Weapon getWeapon() {
        return new Fighter();
    }
}
