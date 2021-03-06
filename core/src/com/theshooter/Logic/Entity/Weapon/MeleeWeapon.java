package com.theshooter.Logic.Entity.Weapon;

import com.badlogic.gdx.math.Vector2;
import com.theshooter.Game;
import com.theshooter.Logic.Entity.Damage;
import com.theshooter.Logic.Entity.Creatures.CreatureEntity;
import com.theshooter.Logic.Entity.Projectile;

public class MeleeWeapon extends Weapon {
    private int splash;

    public MeleeWeapon(WeaponType weaponType,
                       int            level,
                       int            damage,
                       int            w,
                       int            h,
                       Damage.Type    type,
                       int            velocity,
                       long           shotTime,
                       long           shotLifeTime,
                       int splash,
                       CreatureEntity owner) {
        super(weaponType,
              level,
              damage,
              w,
              h,
              type,
              velocity,
              true,
              false,
              1,
              0,
              shotTime,
              shotLifeTime,
              owner);
        this.splash = splash;
    }

    @Override
    public void attack(Vector2 vect) {
        int attacks = splash / 5;
        if (canAttack()) {
            Vector2 attack = new Vector2(getOwner().getX() + getOwner().getWidth() / 2 - getW() / 2,
                                         getOwner().getY() + getOwner().getHeight() / 2 - getH() / 2);
            vect.rotate(-splash / 2);
            for (int i = 0; i < attacks; i++) {
                Damage damage = new Damage(getOwner(), getType(), Math.max(getDamage() / attacks, 1));
                Projectile projectile =
                        new Projectile(
                                damage,
                                (int) attack.x,
                                (int) attack.y,
                                getW(),
                                getH(),
                                vect.x,
                                vect.y,
                                getVelocity(),
                                getShotLifeTime());

                Game.getInstance().getEntityController().addBullet(projectile);
                setLastShot(Game.getInstance().getGameTime());
                setCurClipSize(getCurClipSize() - 1);
                vect.rotate(5);
            }
        }
    }
}
