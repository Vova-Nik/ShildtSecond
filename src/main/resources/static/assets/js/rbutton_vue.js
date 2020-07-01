console.log("rbutton started!!!")
Vue.component('rbutton', {
    data: function () {
        return {
            count: 0,

            styleObject: {
                border: 'black 1px solid',
                borderRadius: '5px',
                cursor: 'pointer',
                textAlign: 'center',
                margin: '5px auto',
                padding: '4px 5%',
                width: 'fit-content',
                minWidth: '16px',
                'font-family': 'sans-serif',
                boxShadow: '0px 0px 0px 0px rgba(0,0,0,0.75)',
            }
        }
    },
    template:
        `<div
             v-on:click = "process" 
             v-bind:style = "styleObject"
             @mouseover = 'hoverOn'
             @mouseleave ='hoverOff'
             @mousedown = 'mousedown'
             @mouseup = 'mouseup'
        >
              {{ title }}
        </div>`,

        props: {
            title: {
                type: String,
                default: 'rBtn',
              },

            // likes: Number,
            // isPublished: Boolean,
            // commentIds: Array,
            // author: Object,
            // callback: Function,
            // contactsPromise: Promise // или любой другой конструктор
          },

    methods: {
        process: function () {
            this.count++;
            console.log("rbutton - ", this.title)

        },
        hoverOn: function(){
            this.styleObject.boxShadow = '0px 0px 2px 2px rgba(0,0,0,0.5)'
        },
        hoverOff: function(){
            this.styleObject.boxShadow = '0px 0px 0px 0px rgba(0,0,0,0.5)'
        },
        mousedown: function(){
            this.styleObject.boxShadow = '0px 0px 1px 1px rgba(0,0,0,0.5)'
        },
        mouseup: function(){
            this.styleObject.boxShadow = '0px 0px 2px 2px rgba(0,0,0,0.5)'
        },
    },

});

