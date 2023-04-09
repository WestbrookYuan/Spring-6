/**
 * @author yty
 * @version 1.0
 * @since 1.0
 **/
public class Test {

    public static void main(String[] args) {
        Weapon fighter = WeaponFactory.getFighter();
        fighter.attack();
        Weapon dagger = WeaponFactory.getDagger();
        dagger.attack();
    }
}
