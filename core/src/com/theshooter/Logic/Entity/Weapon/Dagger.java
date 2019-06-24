package com.theshooter.Logic.Entity.Weapon;

import com.theshooter.Logic.Damage;
import com.theshooter.Logic.Entity.Creatures.CreatureEntity;

public class Dagger extends MeleeWeapon {
    public Dagger(CreatureEntity owner) {
        super(WeaponType.DAGGER,
                10,
                50,
                50,
                Damage.Type.PHYSICAL,
                500,
                400,
                10,
                owner);
    }
}
