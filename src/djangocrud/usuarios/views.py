from django.shortcuts import render
from django.views.generic import CreateView, ListView, UpdateView, DeleteView, DetailView
from .models import Usuario
from .forms import UsuarioForm
from django.core.urlresolvers import reverse_lazy

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
    success_url = reverse_lazy('usuarios:usuarios')
    form_class = UsuarioForm

class EditarUsuario(UpdateView):
    model = Usuario
    template_name = 'editar_usuario.html'
    success_url = reverse_lazy('usuarios:usuarios')
    form_class = UsuarioForm

class EliminarUsuario(DeleteView):
    model = Usuario
    template_name = 'eliminar_usuario.html'
    success_url = reverse_lazy('usuarios:usuarios')
