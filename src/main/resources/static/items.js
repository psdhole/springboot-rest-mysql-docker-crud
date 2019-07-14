var items = [];

function findItem (itemId) {
  return items[findItemKey(itemId)];
}

function findItemKey (itemId) {
  for (var key = 0; key < items.length; key++) {
    if (items[key].id == itemId) {
      return key;
    }
  }
}

var itemService = {
  findAll(fn) {
    axios
      .get('/api/v1/items')
      .then(response => fn(response))
      .catch(error => console.log(error))
  },

  findById(id, fn) {
    axios
      .get('/api/v1/items/' + id)
      .then(response => fn(response))
      .catch(error => console.log(error))
  },

  create(item, fn) {
    axios
      .post('/api/v1/items', item)
      .then(response => fn(response))
      .catch(error => console.log(error))
  },

  update(id, item, fn) {
    axios
      .put('/api/v1/items/' + id, item)
      .then(response => fn(response))
      .catch(error => console.log(error))
  },

  deleteItem(id, fn) {
    axios
      .delete('/api/v1/items/' + id)
      .then(response => fn(response))
      .catch(error => console.log(error))
  }
}

var List = Vue.extend({
  template: '#item-list',
  data: function () {
    return {items: [], searchKey: ''};
  },
  computed: {
    filteredItems() {
      return this.items.filter((item) => {
      	return item.name.indexOf(this.searchKey) > -1
      	  || item.description.indexOf(this.searchKey) > -1
      	  || item.price.toString().indexOf(this.searchKey) > -1
      })
    }
  },
  mounted() {
    itemService.findAll(r => {this.items = r.data; items = r.data})
  }
});

var Item = Vue.extend({
  template: '#item',
  data: function () {
    return {item: findItem(this.$route.params.item_id)};
  }
});

var ItemEdit = Vue.extend({
  template: '#item-edit',
  data: function () {
    return {item: findItem(this.$route.params.item_id)};
  },
  methods: {
    updateItem: function () {
      itemService.update(this.item.id, this.item, r => router.push('/'))
    }
  }
});

var ItemDelete = Vue.extend({
  template: '#item-delete',
  data: function () {
    return {item: findItem(this.$route.params.item_id)};
  },
  methods: {
    deleteItem: function () {
      itemService.deleteItem(this.item.id, r => router.push('/'))
    }
  }
});

var AddItem = Vue.extend({
  template: '#add-item',
  data() {
    return {
      item: {name: '', description: '', price: 0}
    }
  },
  methods: {
    createItem() {
      itemService.create(this.item, r => router.push('/'))
    }
  }
});

var router = new VueRouter({
	routes: [
		{path: '/', component: List},
		{path: '/item/:item_id', component: Item, name: 'item'},
		{path: '/add-item', component: AddItem},
		{path: '/item/:item_id/edit', component: ItemEdit, name: 'item-edit'},
		{path: '/item/:item_id/delete', component: ItemDelete, name: 'item-delete'}
	]
});

new Vue({
  router
}).$mount('#app')
