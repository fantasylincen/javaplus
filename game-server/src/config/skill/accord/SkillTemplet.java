package config.skill.accord;



/**
 * 技能模板
 * @author DXF
 *
 */
public class SkillTemplet {
	private final int					id;
	private String						name;
	private String						desc;
	
	// 针对敌人 ( 0，敌人  1，友方 )
	private byte						rival;
	
	// 获取受技能影响的NPC	
	private ChooseFighters				choose;
	
	// 技能效果
	private SkillEffect					skillEffect;
	
	
	public SkillTemplet( int id ) {
		this.id = id;
	}
	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}

	public SkillEffect getSkillEffect() {
		return skillEffect;
	}
	public void setSkillEffect( SkillEffect skillEffect) {
		this.skillEffect = skillEffect;
	}

	public ChooseFighters getChoose() {
		return choose;
	}
	public void setChoose(ChooseFighters choose) {
		this.choose = choose;
	}

	public byte getRival() {
		return rival;
	}
	public void setRival( byte rival ) {
		this.rival = rival;
	}
	
	@Override
	public String toString() {
		return "SkillTemplet [id=" + id + ", name=" + name + ", desc=" + desc
				+ ", rival=" + rival 
				+ ", choose=" + choose 
				+ ", skillEffect=" + skillEffect 
				+ "]";
	}
	
	public static void main(String[] args) {
	}

	
}
