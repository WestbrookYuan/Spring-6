/**
 * @author yty
 * @version 1.0
 * @since 1.0
 **/
public class Test {
    public static void main(String[] args) {
        WeaponFactory daggerFactory = new DaggerFactory();
        WeaponFactory fighterFactory = new FighterFactory();
        Weapon fighter = fighterFactory.getWeapon();
        Weapon dagger = daggerFactory.getWeapon();

        fighter.attack();
        dagger.attack();


    }
}
