/**
 * @author yty
 * @version 1.0
 * @since 1.0
 **/
public class DaggerFactory extends WeaponFactory{
    @Override
    public Weapon getWeapon() {
        return new Dagger();
    }
}
