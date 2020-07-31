package com.focamacho.eatanddrink.item;

import moriyashiine.sizeentityattributes.SizeEntityAttributes;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.world.World;

public class ItemDrinkMe extends Item {

    public ItemDrinkMe(Settings settings) {
        super(settings);
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        EntityAttributeInstance height = user.getAttributeInstance(SizeEntityAttributes.HEIGHT_MULTIPLIER);
        EntityAttributeInstance width = user.getAttributeInstance(SizeEntityAttributes.WIDTH_MULTIPLIER);
        if(height != null && width != null) {
            height.setBaseValue(height.getValue() - 0.4);
            width.setBaseValue(width.getValue() - 0.4);
        }
        return super.finishUsing(stack, world, user);
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.DRINK;
    }

    @Override
    public SoundEvent getEatSound() {
        return SoundEvents.ENTITY_GENERIC_DRINK;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        EntityAttributeInstance height = user.getAttributeInstance(SizeEntityAttributes.HEIGHT_MULTIPLIER);
        EntityAttributeInstance width = user.getAttributeInstance(SizeEntityAttributes.WIDTH_MULTIPLIER);
        if(height != null && width != null && height.getValue() != 0.125D) {
            return super.use(world, user, hand);
        }
        return TypedActionResult.pass(user.getStackInHand(hand));
    }

    @Override
    public ActionResult useOnEntity(ItemStack stack, PlayerEntity user, LivingEntity entity, Hand hand) {
        EntityAttributeInstance height = entity.getAttributeInstance(SizeEntityAttributes.HEIGHT_MULTIPLIER);
        EntityAttributeInstance width = entity.getAttributeInstance(SizeEntityAttributes.WIDTH_MULTIPLIER);
        if(height != null && width != null && height.getValue() != 0.125D) {
            height.setBaseValue(height.getValue() - 0.4);
            width.setBaseValue(width.getValue() - 0.4);
            stack.decrement(1);
            entity.world.playSound(null, entity.getX(), entity.getY(), entity.getZ(), this.getEatSound(), SoundCategory.NEUTRAL, 1.0F, 1.0F + (entity.world.random.nextFloat() - entity.world.random.nextFloat()) * 0.4F);
            return ActionResult.CONSUME;
        }
        return ActionResult.FAIL;
    }
}
