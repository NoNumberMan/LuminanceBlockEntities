# LuminanceBlockEntities
allows block entities (such as chests, furnaces, dispensers, etc.) to emit light into the world, similarly to how a glowstone block or a redstone lamp would. However, unlike glowstone blocks and redstone lamps, this utility api completely foregoes blockstates. It is therefore perfect for mods that want to add new blocks with many blockstates that also emit light, or for mods that need very fine control over their lighting. ONLY WORKS FOR FABRIC (sorry!)

#Want to make changes to this project?
This project has a permissive MIT license, so bascially, you can do whatever you want with this (as long as the license is included). Fork to your heart's content!

#Want to make a mod with this project?
SETUP
In order to use this api for your mods, simply download the jar (https://www.curseforge.com/minecraft/mc-mods/luminance-block-entities-fabric), and place it somewhere in your Fabric development environment. In your build.gradle file, add

flatDir { dirs 'directory/where/you/placed/the/jar' }
to the repositories section. And, add 

modImplementation "com.nonumberstudios.luminance_block_entities:luminance_block_entities:1.0.0"
to the dependencies section. Of course, change out the '1.0.0' bit with whichever version of this api you're using.

HOW TO USE
Once set up, you can add new block entities like you normally would using fabric. Instead of extending BlockEntity however, your block entity class should extend the LuminanceBlockEntity class provided by the api. This class will need a new parameter LuminanceBehavior. This is an enum that specifies how you decide whether to use your block entity's lighting or your block state's lighting in the world. Here are your options:

IGNORE: only use the lighting from your block entity, ignore block state. This is probably what you want.
FALLBACK: use the lighting from your block entity if your block entity has light. If your lighting is zero, use the block state's lighting as a fallback.
PRIORITY: use the lighting from your block state if your block state has light. If your lighting is zero, use the block entity's lighting as a fallback.
Next you will be prompted to implement the getLuminance method. Simply do whatever you want in here. If you want to update the world's lighting according to whatever getLuminance returns, simply call the updateLuminance method defined in LuminanceBlockEntity. Only call this if your lighting has actually changed! It's somewhat expensive.

Lastly in your mod initialization sequence, you can call LuminanceBlockEntitiesMod.initializeExample() to load an example block entity into the game. This is a very crude block (without texture and such) that can be found in the misc tab. If you place it down you'll see it generates a random lighting value between 0 and 15 every tick, and updates the world lighting accordingly (https://youtu.be/R-09GpRd0F8). Theoretically, doing this in the vanilla style of changing block state every tick would be more expensive, but I'm sure you can find better applications for this api (I sure have, stay tuned😉). You can check out the GlowBlockEntity class to see the implementation.

And that's about it. If you need any help, you can ask in the comments or you can contact me at info@nonumberstudios.com. Good luck programming!
