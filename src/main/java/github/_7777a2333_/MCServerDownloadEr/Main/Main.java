package github._7777a2333_.MCServerDownloadEr.Main;

import java.util.Scanner;
import com.acgist.snail.*;
import com.acgist.snail.context.exception.DownloadException;

public class Main {
	public static void main(String[] args) {
		final Snail snail = Snail.SnailBuilder.newBuilder()
				.enableAllProtocol()
				.buildSync();
		//初始化蜗牛
		String serverVersion, downloadLink = null, legacyServer;
		Scanner enter = new Scanner(System.in);
		boolean cdn;
		//初始化
		System.out.println("MC服务端下载器 1.2.0");
		System.out.println("采用GNU GPLv3协议分发,并含有附加条款");
		System.out.println("原版服务端国内源由BMCLAPI和MCBBS提供高速下载服务.BMCLAPI为公益服务.赞助BMCLAPI可以帮助作者提供更好的高速下载服务");
		System.out.println("赞助BMCLAPI:https://afdian.net/a/bangbang93");
		System.out.println("使用的开源项目：");
		System.out.println("Snail(蜗牛)：https://github.com/acgist/snail");
		System.out.println("其他见开源地址https://github.com/7777a2333/MCServerDownloadEr");
		//显示说明
		int type;
		do {
			System.out.println("输入你要下载的服务端序号:)");
			System.out.println("1   原版服务端   2 Purpur服务端(Paper分支)  3    Spigot服务端   4 CraftBukkit服务端");
			System.out.println("5   MohistMC   6 Fabric服务端            7    远古版本服务端   8 PowerNukkitX基岩版服务端");
			System.out.println("9   基岩版官方服务端(仅Windows 需要解压) 10 Forge安装器");
			type = enter.nextInt();
		} while (type > 11);
		//要求输入服务端类型
		switch (type) {
			case 1 -> {
				System.out.println("输入要下载的服务端版本号(正式版1.2.5及以上 快照13w16a以上)");
				serverVersion = enter.next();
				//要求输入服务端版本,并保存到服务端版本变量内
				int a;
				do {
					System.out.println("选择你要使用的镜像源:(填序号)");
					System.out.println("1 Mojang源(海外) 2 BMCLAPI源(国内) 3 MCBBS源(国内)");
					a = enter.nextInt();
				} while (a > 4);

				//输入下载源编号
				switch (a) {
					case 1 -> downloadLink = "https://www.mcjars.com/get/vanilla-" + serverVersion + ".jar";
					case 2 -> downloadLink = "https://bmclapi2.bangbang93.com/version/" + serverVersion + "/server";
					case 3 -> downloadLink = "https://download.mcbbs.net/version/" + serverVersion + "/server";
				}
			}
			case 2 -> {
				System.out.println("输入要下载的服务端版本号(1.14.4及以上)");
				serverVersion = enter.next();
				//要求输入服务端版本,并保存到服务端版本变量内
				downloadLink = "https://api.purpurmc.org/v2/purpur/" + serverVersion + "/latest/download";
			}
			case 3 -> {
				System.out.println("输入要下载的服务端版本号(1.0.0及以上)");
				serverVersion = enter.next();
				//要求输入服务端版本,并保存到服务端版本变量内

				System.out.println("服务端版本是否小于或等于1.16.5?(是填true,否填false)");
				cdn = enter.nextBoolean();
				//Getbukkit中大于1.16.5(包括)二级域名是download.小于1.16.5二级域名是cdn

				if (!cdn) {
					downloadLink = "https://download.getbukkit.org/spigot/spigot-" + serverVersion + ".jar";
				} else {
					downloadLink = "https://cdn.getbukkit.org/spigot/spigot-" + serverVersion + ".jar";
				}
			}
			case 4 -> {
				System.out.println("输入要下载的服务端版本号(1.0.0及以上)");
				serverVersion = enter.next();
				//要求输入服务端版本,并保存到服务端版本变量内

				System.out.println("服务端版本是否小于或等于1.16.5?(是填true,否填false)");
				cdn = enter.nextBoolean();
				//Getbukkit中大于1.16.5(包括)二级域名是download.小于1.16.5二级域名是cdn

				if (!cdn) {
					downloadLink = "https://download.getbukkit.org/craftbukkit/craftbukkit-" + serverVersion + ".jar";
				} else {
					downloadLink = "https://cdn.getbukkit.org/craftbukkit/craftbukkit-" + serverVersion + ".jar";
				}
			}
			case 5 -> {
				System.out.println("输入要下载的版本号(1.12.2或1.16.5)");
				serverVersion = enter.next();
				//要求输入服务端版本,并保存到服务端版本变量内

				downloadLink = "https://mohistmc.com/api/" + serverVersion + "/latest/download";
			}
			//下载服务端并变量
			case 6 -> {
				System.out.println("输入要下载的版本号(1.14及以上)");
				serverVersion = enter.next();
				//要求输入服务端版本,并保存到服务端版本变量内

				System.out.println("输入要下载的Fabric版本(可使用启动器查看或直接输入'0.14.19')");
				String fabricVersion = enter.next();
				downloadLink = "https://meta.fabricmc.net/v2/versions/loader/" + serverVersion + "/" + fabricVersion + "/0.11.1/server/jar";
			}
			//下载服务端并变量
			case 7 -> {
				System.out.println("输入服务端大版本(仅可输入alpha beta classic)");
				legacyServer = enter.next();
				System.out.println("输入服务端版本(alpha输入'a0.x.x'" +
						" beta输入'b1.x.x' " +
						"classic输入c1.x.x)");
				serverVersion = enter.next();
				downloadLink = "https://files.betacraft.uk/server-archive/" + legacyServer + "/" + serverVersion + ".jar";
			}
			//下载服务端并变量
			case 8 -> {
				System.out.println("将下载PNX-CLI-0.0.8");
				downloadLink = "https://github.com/PowerNukkitX/PNX-CLI/releases/download/0.0.8/PNX-CLI-0.0.8.jar";
			}
			case 9 -> {
				System.out.println("请输入服务端版本(如1.19.63.01,1.20.0.20)");
				serverVersion = enter.next();
				System.out.println("是不是Preview版？(是输入true/否输入false)");
				boolean isPreview = enter.nextBoolean();
				if (isPreview) {
					downloadLink = "https://minecraft.azureedge.net/bin-win/bedrock-server-" + serverVersion + ".zip";
				} else {
					downloadLink = "https://minecraft.azureedge.net/bin-win-preview/bedrock-server-" + serverVersion + ".zip";
				}
			}
			case 10 -> {
				System.out.println("输入要下载的版本号(1.1及以上)");
				serverVersion = enter.next();
				//要求输入服务端版本,并保存到服务端版本变量内

				System.out.println("输入要下载的Forge版本(可使用启动器查看)");
				String forgeVersion = enter.next();
				downloadLink = "https://maven.minecraftforge.net/net/minecraftforge/forge/" + serverVersion + "-" + forgeVersion + "/forge-" + serverVersion + "-" + forgeVersion + "-installer.jar";

			}

		}
		//判断服务端类型
		System.out.println("下载中，请稍后");
		try {
			snail.download(downloadLink);
		} catch (DownloadException e) {
			throw new RuntimeException(e);
		}
		// 等待下载完成
		snail.lockDownload();
		System.out.println("下载完成,服务端在download目录里");
		System.out.println("<输入任意字符并按回车键退出...>");
		System.exit(0);
		//EXIT
	}
}
