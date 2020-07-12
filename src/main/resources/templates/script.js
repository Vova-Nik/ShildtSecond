
Vue.component('rest-button', {
    data: function () {
        return {
            count: 0,
            btnid: 0,
        }
    },

    template: '<div v-on:click="process">Счётчик кликов — {{ count }}</div>',
    props: ['title', 'btn_id'],
    methods: {
        process: function () {
            this.count++;
            console.log("rest-button - ", this.btn_id)
        }
    }
});



Vue.component('rest-card', {
    data: function () {
        return {
            count: 0,
        }
    },

    template: '<div v-on:click="process">{{  }}</div>',
    props: ['title', 'card_id'],
    methods: {
        process: function () {
            this.count++;
            console.log("rest-button - ", this.btn_id)
        }
    }
});

var demo = new Vue({
    el: '#demo',
    data: {
        message: 'Hello, Singree!',
        current_img_ind: 0,
        current_img: "img/v1.jpg",
        vvv: `<h2 style="color: red;">hellow world forever</h1>`,
        imgs: [
            { id: 0, text: 'img/v0.jpg' },
            { id: 1, text: 'img/v1.jpg' },
            { id: 2, text: 'img/v2.jpg' },
            { id: 3, text: 'https://arutyunov.me/wp-content/uploads/2020/04/vue-router.0af7fd7.05f6fab2d3e9e6139626914606edf45a-1024x576.png' },
        ]
    },

    methods: {
        nextImg: function () {
            this.current_img_ind++;
            if (this.current_img_ind == this.imgs.length)
                this.current_img_ind = 0;
            this.current_img = this.imgs[this.current_img_ind].text;
            console.log("vvv nextImg: function ", this.current_img);
        },

        btnClick: function (src) {
            console.log("btnClick " + src)
        },

        tick: function () {
            setTimeout(sayHi, 1000);
        }
    },

});



// new Vue({ el: '#rest-button1' })
// new Vue({ el: '#rest-button2' })

console.log("script performed")



