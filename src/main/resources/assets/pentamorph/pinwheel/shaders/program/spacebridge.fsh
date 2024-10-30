#include pentamorph:raymarch
#include veil:fog

uniform sampler2D Sampler0;

uniform vec4 ColorModulator;
uniform float FogStart;
uniform float FogEnd;
uniform vec4 FogColor;
uniform vec2 ScreenSize;

in float vertexDistance;
in vec4 vertexColor;
in vec2 texCoord0;

out vec4 fragColor;

void main() {
    vec4 color = texture(Sampler0, vec2(1.0 - gl_FragCoord.x / ScreenSize.x, gl_FragCoord.y / ScreenSize.y)) * vertexColor * ColorModulator;
    fragColor = linear_fog(color, vertexDistance, FogStart, FogEnd, FogColor);

    //sampled from Insterstellar
    vec3 dir = rayDirection(45.0, ScreenSize.xy, gl_FragCoord);
    vec3 eye = vec3(0.0, 0.0, 5.0);
    float dist = shortestDistanceToSurface(eye, dir, MIN_DIST, MAX_DIST);

    if (dist > MAX_DIST - EPSILON) {
        // Didn't hit anything
        fragColor = vec4(0.0, 0.0, 0.0, 0.0);
        return;
    }

    fragColor = vec4(1.0, 0.0, 0.0, 1.0);
}
