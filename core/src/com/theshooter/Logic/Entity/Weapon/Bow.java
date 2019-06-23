package com.theshooter.Logic.Entity.Weapon;

import com.theshooter.Logic.Damage;
import com.theshooter.Logic.Entity.Creatures.CreatureEntity;

public class Bow extends OneShotWeapon {
    public Bow(CreatureEntity owner) {
        super(
                WeaponType.BOW,
                15,
                5,
                5,
                Damage.Type.PHYSICAL,
                2000,
                true,
                800,
                100_000_000,
                owner);
    }

    @Override
    public void levelUp() {
        setDamage(getDamage() + 5);
    }
}
