module.exports = {
  title: 'java面试那些事',
  description: 'java面试那些事',
  dest: './dist',    // 设置输出目录
  base: '/java/', // 设置站点根路径
  
  themeConfig: {
    repo: 'https://github.com/javayue' ,
    nav: [
      { text: '首页', link: '/' },
      { text: 'java面试', link: '/jdk/' },
      { text: 'javasrcipt面试', link: '/javasrcipt/' },
      { text: 'Vue框架学习', link: '/xiao/' },
      { text: '个人首页', link: 'http://www.xiaowenjie.cn' },
    ],
    sidebar:  {
	  '/jdk/': [{
	      title: 'JDK笔记',
	      collapsable: false,
	      children: [
	        '',
	      ]
	  }],      
    }
  }, //themeConfig
  configureWebpack: {
    resolve: {
      alias: {
        '@img': 'images'
      }
    }
  }
}