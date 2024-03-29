package com.zcj.util;

import java.util.Random;

import com.xxjwd.classes.YouJianDiZhi;
import com.xxjwd.classes.YouJianFuJian;

public class StringUtil {
	
	/** 判断是否是 "" 或者 null */
	
	public static String strFuHaoKaiShi = "$$";
	public static String strFuHaoFenGe = "''";
	public static String strFuHaoYouJian = ";;";
	
	public static String ShowFullSize(int filesize)
	{
		
		if (filesize <= 0) return "N/A";
		else if (filesize < 1024 ) return filesize + "B";
		else if (filesize < 1024 * 1024) return (filesize / 1024) + "KB";
		else return (filesize / 1024 /1204) + "MB";
	}
	
	public static YouJianFuJian[] StringToYouJianFuJian(String strYjfj)
	{
		if (strYjfj.contains(strFuHaoKaiShi) == false) return null;
		String[] strYjfjs = strYjfj.split(strFuHaoYouJian);
		YouJianFuJian[] yjfjs = new YouJianFuJian[strYjfjs.length];
		for(int i=0;i<yjfjs.length;i++)
		{
			yjfjs[i] = new YouJianFuJian();
			yjfjs[i].LoadFrom(strYjfjs[i]);
		}
		return yjfjs;
	}
	
	public static YouJianDiZhi[] StringToYouJianDiZhi(String strYjdz)
	{
		if (strYjdz.contains(strFuHaoKaiShi) == false) return null;
		String[] strYjdzs = strYjdz.split(strFuHaoYouJian);
		YouJianDiZhi[] yjdzs = new YouJianDiZhi[strYjdzs.length];
		for(int i=0;i<yjdzs.length ;i++)
		{
			yjdzs[i] = new YouJianDiZhi();
			yjdzs[i].LoadFrom(strYjdzs[i]);
		}
		return yjdzs;
	}
	
	public static String ShowFullString(YouJianDiZhi[] yjdzs)
	{
		if (yjdzs == null ) return "";
		if (yjdzs.length == 0) return "";
		if (yjdzs.length == 1) return yjdzs[0].ShowFullString();
		String str = "";
		for(int i=0 ;i < yjdzs.length ;i ++)
		{
			str += yjdzs[i].ShowFullString();
			if (i < yjdzs.length -1) str += ";";
		}
		return str;
	}
	
	public static boolean isEmpty(String str) {
		if (str != null && !"".equals(str)) {
			return false;
		} else {
			return true;
		}
	}
	public static String getSaying() {
		Random random = new Random();
		int num = random.nextInt(SAYING.length);
		String str = SAYING[num];
		if (str.indexOf(";") != -1) {
			str = str.replace(";", "-----");
		} 
		
		return str;
	}
	private static final String[] SAYING = {
		"战争打响，真理阵亡。;埃斯库罗斯",
		"人类不结束战争，战争就会结束人类。;约翰·F·肯尼迪",
		"战争无法决定谁是正确的，只能决定谁是存活的一方。;罗素 ",
		"没有海军陆战队的军舰就像没有扣子的衣服。 ;美国海军上将 大卫·D·波特",
		"传媒是我们主要的思想武器。;赫鲁晓夫",
		"不管你喜不喜欢，历史都站在我们这边，而你们将被我们埋葬！;赫鲁晓夫",
		"敌人在的射程内，彼此彼此。;步兵日记 ",
		"枚战斧巡航导弹的造价：90万美元。 ",
		"一架F-22猛禽战斗机的造价：1.35亿美元",
		"一架F-117A“夜鹰”隐形战斗机的造价：1.22亿美元 ",
		"一架B-2轰炸机的造价：22亿美元",
		"只要有人类存在，就会有战争 。;爱因斯坦 ",
		"对着敌人瞄准。;美国 火箭发射器上的使用说明 ",
		"任何诚实的军事指挥官都会承认他在动用军队时犯下过错误。;罗伯特·麦克纳马拉",
		"你可以用暴力获得权利，但你会如坐针毡。;叶利钦 ",
		"世上最致命的武器是陆战队和他的枪！;美国将军 约翰 J. 珀欣",
		"站在理想一边的人不能称为恐怖分子。;阿拉法特",
		"没有比挨了枪子还安然无恙更爽的了。 ;丘吉尔",
		"对于没经历过战争的人来说，战争当然是轻松愉快的。;伊拉斯谟 ",
		"友军伤害——不友好。",
		"就像士兵们结束战争一样，外交官往往是开始战争的关键。",
		"科技在被应用之前，都是道德中立的。用之善则善，用之恶则恶。;威廉·吉布森",
		"老家伙们宣战，上前线的却是小伙子们。;赫伯特·胡佛 第三十一位美国总统",
		"战场上的指挥官总是对的，屁股后面的司令部总是错的，除非举出反例。;鲍威尔 美国前国务卿",
		"自由并不是免费的，但是美国海军陆战队会帮你出大头。",
		"我不知道第三次世界大战人类会用什么武器，但第四次世界大战会是棍棒和石头。 ;爱因斯坦",
		"你总是知道该做什么，但难的是去做。",
		"知己知彼，百战不殆。;孙子",
		"几乎所有人都能矗立在逆境中，但是如果你想知道一个人的真实一面，请给他力量。;林肯",
		"如果我们不能向国家证明我们的理想更有价值，我们最好重新检视我们的推理,罗伯特·麦克纳马拉",
		"自由之树必须用暴君和爱国者的鲜血一遍又一遍的洗刷;托马斯·杰斐逊 (美国政治家, 第三任总统, indpdc宣言的起草人)",
		"如果机翼飞得比机身还快，那八成是架直升机——所以这玩意不安全。",
		"5秒的引信只烧3秒;步兵日记",
		"如果进攻太过顺利，那么恭喜你上当了。;士兵日记 ",
		"没有任何作战计划在与敌人遭遇后还有效。;鲍威尔",
		"当保险丝被拔掉后，手雷先生就不再是我们的朋友了。;美国陆军训练提示",
		"人有生死，国有兴亡，而意志长存。;肯尼迪",
		"枚标枪反坦克导弹的造价：8万美元 ",
		"以道作人主，不以兵强于天下",
		"如果你记不住，66式(XD)就是冲着你来的 ",
		"只有两种人了解陆战队：陆战队和它的敌人，其他人都在扯二手淡。",
		"我身边的陆战队员越多，我越Happy 。;美国陆军克拉克将军 ",
		"别忘了，你的武器是由出价最低的竞标商制造的。 ",
		"记住要透过现象看本质。不要因为害怕真相的肮脏而退缩。",
		"嘿，看开点，他们也许没子弹了。;士兵日记",
		"这个世界不会接受专政与支配。;戈尔巴乔夫 ",
		"暴君们总会有些微不足道的美德，他们会支持法律，然后摧毁它。;伏尔泰 ",
		"英雄不见得比别人更勇敢，但他们多坚持了5分钟。;里根",
		"最后，很幸运，我们曾“如此”接近核战争，但避免了。;罗伯特·麦克纳马拉",
		"有些人活了一辈子，一直希望干些什么大事，但陆战队员们没有那个问题。 ;里根",
		"一般来讲，直接降落在刚刚被轰炸过的区域是不明智的。 ;美国空军中将",
		"我们之所以能够在床上睡安稳觉，是因为大兵们正在为我们站岗。;乔治·奥韦尔",
		"如果你一开始没搞定，赶快呼叫空中支援。 ",
		"曳光弹照亮的不光是敌人。;美国陆军条例",
		"团队协作很重要，它能让别人替你挨枪子。",
		"只有和平才会获得最终的胜利。 ;爱默生",
		"在一个恐怖主义可能拥有科技的世界里，如果我们不采取行动，将会十分后悔。;康多莉扎·赖斯 (美国第66任国务卿) ",
		"兵者，诡道也。;孙子",
		"人类的可靠性和核武器，这对不稳定的组合会毁灭许多国家。;罗伯特·麦克纳马拉",
		"在战争中，输赢、生死只是一念之差。 ;道格拉斯·麦克阿瑟将军",
		"你不能说文明没有进步——至少在每次战争中，他们都换种新方法来干掉你。",
		"在你明白核武器怎么用之前，这玩意就能毁灭国家了。;罗伯特·麦克纳马拉",
		"指挥的家伙不配当英雄，真正的英雄在战斗中诞生。",
		"任何合格的士兵都应该反对战争，同时，也有着值得为之战斗的东西。",
		"不想打胜仗就别去送死。 ",
		"难知如阴，动如雷震。;孙子",
		"衷心想参加战争的人，肯定没真正体验过。;拉里 瑞福斯",
		"说“笔强于剑”的人肯定没见过自动武器。;道格拉斯·麦克阿瑟将军",
		"邪恶的得逞依靠善良的无为 ;埃德蒙·伯克",
		"If a man has done his best, what else is there?;乔治 S. 巴顿将军",
		"手雷的爆炸半径总是比你的跳跃距离多一点。",
		"暴君们一边夸耀他如何爱民，一边在残害百姓。",
		"每个暴君都相信自由——他自己的自由。;阿尔伯特·哈伯德(美国作家)",
		"相对于战争结束来说，我们更希望所有的战争本就没有爆发。;富兰克林·D·罗斯福",
		"成功不是终点，失败也不是终结，只有勇气才是永恒。;温斯顿·丘吉尔 ",
		"没有必胜的决心，战争必败无疑。;道格拉斯·麦克阿瑟 ",
		"所有的战争都是内战，因为所有的人类都是同胞。;弗朗索瓦·费奈隆 ",
		"在战争中，第二名是没有奖赏的。;奥玛·布莱德利将军 ",
		"好动与不满足是进步的第一必需品。",
		"time is money",
		"不论多么师出有名，也决不能因此误以为战争是无罪的。;厄尼斯．海明威",
		"为已死的人哀悼是愚蠢的，我们反而应该感谢上帝曾经赐予他生命。;乔治．巴顿将军",
		"战争的目的不是要你为国牺牲，而是要让该死的敌人为他的国家牺牲。;乔治．巴顿将军",
		"一死则百了 － 没有人，就不会有战争。;约瑟夫．斯大林",
		"一个人的死亡是天大的不幸，而数百万人的死亡则只是简单的统计数字。;约瑟夫．斯大林",
		"幸好战争是如此地丑恶，否则我们恐怕会爱上它。;罗伯特．李",
		"我自己都忍不住开始鼓掌直到我意识到自己是桑德兰的主席。 ;Peter Reid,在博格坎普对桑德兰的比赛中入球后",
		"我告诉我儿子Josh “霍华德·威尔金森希望爸爸为英格兰队比赛。”他把这件事告诉了我的女儿Olivia，然后他们的眼中都含着泪水问我‘那是不是说明你不再为阿森纳踢球了？;Lee Dixon ",
		"战士会愿意为了一小块勋章而奋战到底。;拿破仑．波拿巴特",
		"害怕战败的人一定会战败。;拿破仑．波拿巴特",
		"绝不可与同一敌人对峙太久，否则他会学会你所有的战术。;拿破仑．波拿巴特",
		"不怀念苏联的人没心没肺，想重回苏联的人无头无脑。;普京",


};

	public static String YouJianDiZhiToString(YouJianDiZhi[] to) {
		// TODO Auto-generated method stub
		String result= "";
		if (to == null || to.length == 0) return result;
		
		for(int i=0;i<to.length ;i++)
		{
			result += StringUtil.strFuHaoKaiShi + to[i].getAddress() + StringUtil.strFuHaoFenGe + to[i].getDisplayname();
			if (i< to.length -1 ) result += StringUtil.strFuHaoYouJian;
		}
		return result;
	}

}