package io.github.jadedchara.pentamorph.mixin;

import foundry.veil.api.quasar.particle.ParticleEmitter;
import foundry.veil.api.quasar.particle.ParticleSystemManager;
import io.github.jadedchara.pentamorph.common.util.misc.PSMAccess;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.util.List;

@Mixin(ParticleSystemManager.class)
public abstract class PSMMixin implements PSMAccess {
	@Shadow
	@Final
	private List<ParticleEmitter> particleEmitters;

	//@Override
	public void erase(ParticleEmitter emitter){
		for (ParticleEmitter particleEmitter : this.particleEmitters) {
			if (particleEmitter == emitter){
				particleEmitters.remove(emitter);
			}
		}

	}
	public List<ParticleEmitter> getParticleEmitters(){
		return particleEmitters;
	}

}
