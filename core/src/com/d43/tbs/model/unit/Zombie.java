package com.d43.tbs.model.unit;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Zombie extends MeleeUnit {

	public Zombie(TextureRegion textureRegion, float x, float y, float width, float height) {
		super(textureRegion, x, y, width, height);
		
		this.setRangeMovement(3);
		this.setRangeAttack(1);
		this.setHp(16);
		this.setDamage(24);
		this.setIsEnemy(true);
	}
	
	@Override
	public void draw(SpriteBatch batch) {
		super.draw(batch);
//		this.controller.handle();
	}
	
	public Unit clone() {
		return new Zombie(this.getTextureRegion(), this.getBounds().getX(), this.getBounds().getY(), this.getBounds().getBoundingRectangle().width, this.getBounds().getBoundingRectangle().height);
	}
}
