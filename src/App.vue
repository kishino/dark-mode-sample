<template>
    <v-ons-page>
      <v-ons-toolbar>
        <div class="center">{{ title }}</div>
        <div class="right">
          <v-ons-toolbar-button>
            <v-ons-icon icon="ion-navicon, material: md-menu"></v-ons-icon>
          </v-ons-toolbar-button>
        </div>
      </v-ons-toolbar>
      <p style="text-align: center">
        <v-ons-button @click="toggleTheme">テーマを変更</v-ons-button>
      </p>
      <p class="text-card">
        テキストテキストテキストテキストテキストテキストテキストテキストテキストテキスト
      </p>
    </v-ons-page>
</template>
<script>
  export default{
    data() {
      return {
        title: 'My app',
        dark: false
      }
    },
    beforeCreate() {
      this.$ons.disableAutoStatusBarFill()
    },
    mounted() {
      this.$ons.ready(() => {
        // Use matchMedia to check the user preference
        const prefersDark = window.matchMedia('(prefers-color-scheme: dark)')

        this.changeTheme(prefersDark.matches)

        // Listen for changes to the prefers-color-scheme media query
        prefersDark.addListener((mediaQuery) => this.changeTheme(mediaQuery.matches))
      })
    },
    methods: {
      toggleTheme() {
        this.changeTheme(!this.dark)
      },
      changeTheme(dark) {
        this.dark = dark
        document.body.classList.toggle('dark', dark)
        if (dark) {
          document.getElementById('theme-css').setAttribute('href', 'dark-onsen-css-components.css')
          if (this.$ons.platform.isWebView()) {
            window.StatusBar.styleLightContent()
            window.StatusBar.backgroundColorByHexString('#181818')
            window.plugins.webviewcolor.change('#0d0d0d')
          }
        } else {
          document.getElementById('theme-css').setAttribute('href', 'onsen-css-components.css')
          if (this.$ons.platform.isWebView()) {
            window.StatusBar.styleDarkContent()
            window.StatusBar.backgroundColorByHexString('#fafafa')
            window.plugins.webviewcolor.change('#efeff4')
          }
        }
      }
    }
  }
</script>

<style>
  :root {
    --text-card-bg-color: #ddd;
  }
  body.dark {
    --text-card-bg-color: #444;
  }
  .text-card {
    background-color: var(--text-card-bg-color);
    border-radius: 8px;
    padding: 12px;
    margin: 0 16px;
    text-align: center;
  }
</style>
