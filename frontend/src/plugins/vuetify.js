import '@fortawesome/fontawesome-free/css/all.css'
import Vue from 'vue';
import Vuetify from 'vuetify/lib';
import { preset } from 'vue-cli-plugin-vuetify-preset-fortnightly/preset'

Vue.use(Vuetify);
const options = {
  breakpoint: { scrollbarWidth: 12 },
  theme: {
    themes: {
      light: { primary: '#01579b' },
    },
  },
}

export default new Vuetify({
  // The provided global preset is first merged with defaults
  preset,
  // User options are then merged, overwriting defaults and the global preset
  ...options,
  icons: {
    iconfont: 'fa',
  },
})

