from django.shortcuts import render
from django.views.generic import CreateView, ListView, UpdateView, DeleteView, DetailView
from .models import Usuario
from .forms import UsuarioForm, EditForm
from django.core.urlresolvers import reverse_lazy
from django.contrib.auth import login, authenticate
from django.contrib.auth.models import User
# Create your views here.

class Usuarios(ListView):
    model = Usuario
    template_name = 'usuarios.html'

class VerUsuario(DetailView):
    model = Usuario
    template_name = 'usuario.html'
    context_object_name = 'usuario'

class CrearUsuario(CreateView):
    model = Usuario
    template_name = 'crear_usuario.html'
    #success_url = reverse_lazy('usuarios:usuarios')
    form_class = UsuarioForm
    def form_valid(self, form):
        #usuario = form.save(commit = False)
        usuario = form.save()
        usuario.refresh_from_db()
        
        Usuario.objects.create(usuario=usuario)
        
        usuario.save()
        raw_password = form.cleaned_data.get('password1')
        usuario = authenticate(username=usuario.username, password=raw_password)
        login(self.request, usuario)
        return super(CrearUsuario, self).form_valid(form)

    def get_success_url(self):
        return reverse_lazy('home')   

class EditarUsuario(UpdateView):
    model = Usuario
    template_name = 'editar_usuario.html'
    #success_url = reverse_lazy('usuarios:usuarios') 
    form_class = EditForm
    
    def form_valid(self, form):
        usuario = form.save()
        usuario.refresh_from_db()
        raw_password = form.cleaned_data.get('password')
        usuario.usuario.set_password(raw_password)
        usuario.usuario.username = form.cleaned_data.get('username')
        usuario.usuario.first_name = form.cleaned_data.get('first_name')
        usuario.usuario.last_name = form.cleaned_data.get('last_name')
        usuario.usuario.email = form.cleaned_data.get('email')
        #usuario = form.save()
        print(usuario.usuario.username)
        
        #usuario.refresh_from_db()
        usuario.usuario.save()
        usuario.save()
        
        #usuario.refresh_from_db()
        #print(usuario.username)
        
        return super(EditarUsuario, self).form_valid(form)
    

    def get_success_url(self):
        return reverse_lazy('home')

class EliminarUsuario(DeleteView):
    model = Usuario
    template_name = 'eliminar_usuario.html'
    success_url = reverse_lazy('usuarios:usuarios'
)
