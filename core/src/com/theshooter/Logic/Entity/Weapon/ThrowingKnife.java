package com.theshooter.Logic.Entity.Weapon;


import com.badlogic.gdx.math.Vector2;
import com.theshooter.Game;
import com.theshooter.Logic.Entity.Damage;
import com.theshooter.Logic.Entity.Creatures.CreatureEntity;
import com.theshooter.Logic.Entity.Projectile;

public class ThrowingKnife extends OneShotWeapon {
    public ThrowingKnife(int level, CreatureEntity owner) {
        super(
                WeaponType.THROWING_KNIFE,
                level,
                10,
                10,
                10,
                Damage.Type.PHYSICAL,
                1500,
                true,
                400,
                1500,
                owner
        );
        for (int i = 0; i < level; i++)
            levelUp();
        setClipSize(3);
    }

    @Override
    public void attack(Vector2 vect) {
        if (canAttack()) {
            for (int i = 0; i < 3; i++) {
                if (getCurClipSize() > 0) {
                    Damage damage = new Damage(getOwner(), getType(), getDamage());
                    vect.rotate((float)Math.pow(-1, i) * i * 15);
                    Projectile projectile =
                            new Projectile(
                                    damage,
                                    getOwner().getX() + getOwner().getWidth() / 2 - getW() / 2,
                                    getOwner().getY() + getOwner().getHeight() / 2 - getH() / 2,
                                    getW(),
                                    getH(),
                                    vect.x,
                                    vect.y,
                                    getVelocity(),
                                    getShotLifeTime());

                    Game.getInstance().getEntityController().addBullet(projectile);
                    setLastShot(Game.getInstance().getGameTime());
                    setCurClipSize(getCurClipSize() - 1);
                }
            }
        }
    }

    @Override
    public void levelUp() {
        setDamage(getDamage() + 3);
    }
}
