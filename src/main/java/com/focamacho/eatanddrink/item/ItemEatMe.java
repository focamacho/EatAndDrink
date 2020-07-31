package com.focamacho.eatanddrink.item;

import moriyashiine.sizeentityattributes.SizeEntityAttributes;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemEatMe extends Item {

    public ItemEatMe(Settings settings) {
        super(settings);
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        EntityAttributeInstance height = user.getAttributeInstance(SizeEntityAttributes.HEIGHT_MULTIPLIER);
        EntityAttributeInstance width = user.getAttributeInstance(SizeEntityAttributes.WIDTH_MULTIPLIER);
        if(height != null && width != null) {
            height.setBaseValue(height.getValue() + 0.4);
            width.setBaseValue(width.getValue() + 0.4);
        }
        return super.finishUsing(stack, world, user);
    }

}
