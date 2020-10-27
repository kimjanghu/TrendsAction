<template>
  <v-row justify="center">
    <v-dialog
      v-model="dialog"
      persistent
      max-width="400px"
    >
      <v-card>
        <v-card-title>
          <span class="headline">Login</span>
        </v-card-title>
        <v-card-text>
          <!-- <GoogleLogin 
            :params="params" 
            :renderParams="renderParams" 
            
          ></GoogleLogin> -->
        </v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn
            color="blue darken-1"
            text
            @click="changeDialog"
          >
            Close
          </v-btn>
          <v-btn
            color="blue darken-1"
            text
            @click="changeDialog"
          >
            Save
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </v-row>
</template>

<script>
// import GoogleLogin from 'vue-google-login'

export default {
  name: 'UsersLoginForm',
  data() {
    return {
      params: {
        client_id: "xxxxxx"
      },
      renderParams: {
        width: 250,
        height: 50,
        longtitle: true
      }
    }
  },
  props: {
    dialog: {
      type: Boolean
    }
  },
  components: {
    // GoogleLogin
  },
  methods: {
    changeDialog() {
      this.$emit('change-dialog', false)
    }
  }
}
</script>