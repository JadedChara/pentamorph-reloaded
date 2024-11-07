package io.github.jadedchara.pentamorph.common.util.misc;

import foundry.veil.api.quasar.particle.ParticleEmitter;
import foundry.veil.api.quasar.particle.ParticleSystemManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.List;

//@Mixin(ParticleSystemManager.class)
public interface PSMAccess {

	void erase(ParticleEmitter emitter);

	List<ParticleEmitter> getParticleEmitters();


}
