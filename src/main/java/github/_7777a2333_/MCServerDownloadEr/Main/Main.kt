package github._7777a2333_.MCServerDownloadEr.Main

import com.acgist.snail.Snail
import com.acgist.snail.context.exception.DownloadException
import java.util.Scanner
import kotlin.system.exitProcess

/**
 * @author 7777a2333
 */
object Main {
    @JvmStatic
    fun main(args: Array<String>) {
        val snail = Snail.SnailBuilder.newBuilder()
                .enableAllProtocol()
                .buildSync()
        //初始化蜗牛
        val serverVersion: String
        var downloadLink: String? = null
        val legacyServer: String
        val read = Scanner(System.`in`)
        val cdn: Boolean
        //初始化
        println("MC服务端下载器 1.2.0")
        println("采用GNU GPLv3协议分发,并含有附加条款")
        println("原版服务端国内源由BMCLAPI和MCBBS提供高速下载服务.BMCLAPI为公益服务.赞助BMCLAPI可以帮助作者提供更好的高速下载服务")
        println("赞助BMCLAPI:https://afdian.net/a/bangbang93")
        println("使用的开源项目：")
        println("Snail(蜗牛)：https://github.com/acgist/snail")
        println("其他见开源地址https://github.com/7777a2333/MCServerDownloadEr")
        //显示说明
        var type: Int
        do {
            println("---------------------------------------------------")
            println("输入你要下载的服务端序号:)")
            println("1   原版服务端   2 Purpur服务端(Paper分支)  3    Spigot服务端   4 CraftBukkit服务端")
            println("5   MohistMC   6 Fabric服务端            7    远古版本服务端   8 PowerNukkitX基岩版服务端")
            println("9 Forge安装器")
            println("附加功能:")
            println("-1 关于 -2 生成服务端启动脚本")
            type = read.nextInt()
        } while (type > 10)
        when (type) {
            1 -> {
                println("输入要下载的服务端版本号(正式版1.2.5及以上 快照13w16a以上)")
                serverVersion = readlnOrNull().toString()
                //要求输入服务端版本,并保存到服务端版本变量内
                var from: Int
                do {
                    println("选择你要使用的镜像源:(填序号)")
                    println("1 Mojang源(海外) 2 BMCLAPI源(国内) 3 MCBBS源(国内)")
                    from = read.nextInt()
                } while (from > 4)
                when (from) {
                    1 -> downloadLink = "https://www.mcjars.com/get/vanilla-$serverVersion.jar"
                    2 -> downloadLink = "https://bmclapi2.bangbang93.com/version/$serverVersion/server"
                    3 -> downloadLink = "https://download.mcbbs.net/version/$serverVersion/server"
                }
            }

            2 -> {
                println("输入要下载的服务端版本号(1.14.4及以上)")
                serverVersion = readlnOrNull().toString()
                //要求输入服务端版本,并保存到服务端版本变量内
                downloadLink = "https://api.purpurmc.org/v2/purpur/$serverVersion/latest/download"
            }

            3 -> {
                println("输入要下载的服务端版本号(1.0.0及以上)")
                serverVersion = readlnOrNull().toString()
                //要求输入服务端版本,并保存到服务端版本变量内
                println("服务端版本是否小于或等于1.16.5?(是填true,否填false)")
                cdn = read.nextBoolean()
                //Getbukkit中大于1.16.5(包括)二级域名是download.小于1.16.5二级域名是cdn
                downloadLink = if (!cdn) {
                    "https://download.getbukkit.org/spigot/spigot-$serverVersion.jar"
                } else {
                    "https://cdn.getbukkit.org/spigot/spigot-$serverVersion.jar"
                }
            }

            4 -> {
                println("输入要下载的服务端版本号(1.0.0及以上)")
                serverVersion = readlnOrNull().toString()
                //要求输入服务端版本,并保存到服务端版本变量内
                println("服务端版本是否小于或等于1.16.5?(是填true,否填false)")
                cdn = read.nextBoolean()
                //Getbukkit中大于1.16.5(包括)二级域名是download.小于1.16.5二级域名是cdn
                downloadLink = if (!cdn) {
                    "https://download.getbukkit.org/craftbukkit/craftbukkit-$serverVersion.jar"
                } else {
                    "https://cdn.getbukkit.org/craftbukkit/craftbukkit-$serverVersion.jar"
                }
            }

            5 -> {
                println("输入要下载的版本号(1.12.2或1.16.5)")
                serverVersion = readlnOrNull().toString()
                //要求输入服务端版本,并保存到服务端版本变量内
                downloadLink = "https://mohistmc.com/api/$serverVersion/latest/download"
            }

            6 -> {
                println("输入要下载的版本号(1.14及以上)")
                serverVersion = readlnOrNull().toString()
                //要求输入服务端版本,并保存到服务端版本变量内
                println("输入要下载的Fabric版本(可使用启动器查看或直接输入'0.14.19')")
                val fabricVersion = readlnOrNull().toString()
                downloadLink = "https://meta.fabricmc.net/v2/versions/loader/$serverVersion/$fabricVersion/0.11.1/server/jar"
            }

            7 -> {
                println("输入服务端大版本(仅可输入alpha beta classic)")
                legacyServer = readlnOrNull().toString()
                println("输入服务端版本(alpha输入'a0.x.x'" +
                        " beta输入'b1.x.x' " +
                        "classic输入c1.x.x)")
                serverVersion = readlnOrNull().toString()
                downloadLink = "https://files.betacraft.uk/server-archive/$legacyServer/$serverVersion.jar"
            }

            8 -> {
                println("将下载PNX-CLI-0.0.8")
                downloadLink = "https://github.com/PowerNukkitX/PNX-CLI/releases/download/0.0.8/PNX-CLI-0.0.8.jar"
            }


            9 -> {
                println("输入要下载的版本号(1.1及以上)")
                serverVersion = readlnOrNull().toString()
                //要求输入服务端版本,并保存到服务端版本变量内
                println("输入要下载的Forge版本(可使用启动器查看)")
                val forgeVersion = readlnOrNull().toString()
                downloadLink = "https://maven.minecraftforge.net/net/minecraftforge/forge/$serverVersion-$forgeVersion/forge-$serverVersion-$forgeVersion-installer.jar"
            }
        }
        //判断服务端类型
        println("下载中，请稍后")
        try {
            snail.download(downloadLink)
        } catch (e: DownloadException) {
            throw RuntimeException(e)
        }
        // 等待下载完成
        snail.lockDownload()
        println("下载完成,服务端在运行目录的download目录里")
        exitProcess(0)
        //EXIT
    }
}
