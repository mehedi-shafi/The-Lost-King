package game.pack;

import java.util.HashMap;
import java.util.Map;

import org.newdawn.slick.Music;
import org.newdawn.slick.Sound;

public class AudioPlayer {
	public static Map<String, Sound> soundMap = new HashMap<String, Sound>();
	public static Map <String, Music> musicMap = new HashMap <String, Music>();
	
	public static void load(){
		try{
			//Musics
			musicMap.put("battle", new Music ("res/audios/in_game_music.ogg"));
			musicMap.put("menu", new Music("res/audios/main_menu.ogg"));
			
			//Sounds
			soundMap.put("click",new Sound("res/audios/click.ogg"));
			soundMap.put ("punch", new Sound("res/audios/hero_punch.ogg"));
			soundMap.put("fire", new Sound ("res/audios/gun_fire.ogg"));
			soundMap.put("gear", new Sound ("res/audios/gear_second.ogg"));
			soundMap.put("haki", new Sound("res/audios/haki.ogg"));
			soundMap.put("over", new Sound ("res/audios/game_over.ogg"));
			soundMap.put("die", new Sound ("res/audios/death.ogg"));
		}
		catch (Exception e){
			System.out.println("Music error");
		}
	}
	
	public static Music getMusic(String name){
		return musicMap.get(name);
	}
	
	public static Sound getSound (String key){
		return soundMap.get(key);
	}
}
